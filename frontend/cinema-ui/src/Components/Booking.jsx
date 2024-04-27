import { useState, useEffect } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Modal, Button, Form } from 'react-bootstrap';
import { apiBaseUrl } from '../ApiConfig';

const BookingComponent = () => {
    const [bookings, setBookings] = useState([]);
    const [booking, setBooking] = useState({});
    const [showDetailModal, setShowDetailModal] = useState(false);
    const [showCreateModal, setShowCreateModal] = useState(false);
    const [showConfirmationModal, setShowConfirmationModal] = useState(false);
    const [bookingToCancel, setBookingToCancel] = useState(null);
    const [filter, setFilter] = useState({
        genre: '',
        startDate: '',
        endDate: ''
    });

   
    const genres = [
        'ACTION',
        'ADVENTURE',
        'COMEDY',
        'DRAMA',
        'FANTASY',
        'HORROR',
        'MUSICALS',
        'MYSTERY',
        'ROMANCE',
        'SCIENCE_FICTION',
        'SPORTS',
        'THRILLER',
        'WESTERN'
    ];

    
    const fetchBookings = async () => {
        try {
            const response = await axios.get(`${apiBaseUrl}/booking`);
            setBookings(response.data);
        } catch (error) {
            console.error('Error fetching bookings:', error);
        }
    };


    const fetchBookingById = async (id) => {
        try {
            const response = await axios.get(`${apiBaseUrl}/booking/${id}`);
            setBooking(response.data);
            setShowDetailModal(true);
        } catch (error) {
            console.error('Error fetching booking by ID:', error);
        }
    };


    const createBooking = async (newBooking) => {
        try {
            const response = await axios.post(`${apiBaseUrl}/booking`, newBooking);
            setBookings([...bookings, response.data]);
            fetchBookings(); 
        } catch (error) {
            console.error('Error creating booking:', error);
        }
    };

   
    const updateBooking = async (updatedBooking) => {
        try {
            const response = await axios.put(`${apiBaseUrl}/booking`, updatedBooking);
            const updatedBookings = bookings.map((booking) => {
                if (booking.id === response.data.id) {
                    return response.data;
                }
                return booking;
            });
            setBookings(updatedBookings);
        } catch (error) {
            console.error('Error updating booking:', error);
        }
    };

    
    const cancelBooking = async (id) => {
        try {
            await axios.delete(`${apiBaseUrl}/booking/cancelBooking?bookingId=${id}`);
            setBookings(bookings.filter((booking) => booking.id !== id));
        } catch (error) {
            console.error('Error canceling booking:', error);
        }
    };

   
    const fetchBookingsByGenreAndDates = async (genre, startDate, endDate) => {
        try {
            const response = await axios.get(`${apiBaseUrl}/booking/getBygenreAndDates`, {
                params: {
                    category: genre,
                    startDate: startDate,
                    endDate: endDate
                }
            });
             if (response.data.length === 0) {
            alert('No hay reservas con ese género de película en las fechas ingresadas.');
            
        } else {
            
            setBookings(response.data);
        }
            
        } catch (error) {
            console.error('Error fetching bookings by genre and dates:', error);
        }
    };

 
    const handleShowDetailModal = (booking) => {
        setBooking(booking);
        setShowDetailModal(true);
    };

    const handleCloseDetailModal = () => {
        setShowDetailModal(false);
    };

    const handleShowCreateModal = () => {
        setShowCreateModal(true);
    };

    const handleCloseCreateModal = () => {
        setShowCreateModal(false);
    };

    const handleShowConfirmationModal = (booking) => {
        setBookingToCancel(booking);
        setShowConfirmationModal(true);
    };

    const handleCloseConfirmationModal = () => {
        setShowConfirmationModal(false);
        setBookingToCancel(null);
    };

    const handleConfirmCancel = async () => {
        if (bookingToCancel) {
            await cancelBooking(bookingToCancel.id);
            setBookingToCancel(null);
            handleCloseConfirmationModal();
        }
    };

    const handleFilterChange = (e) => {
        const { name, value } = e.target;
        setFilter((prevFilter) => ({
            ...prevFilter,
            [name]: value
        }));
    };

    const handleFilterSubmit = (e) => {
        e.preventDefault();
        fetchBookingsByGenreAndDates(filter.genre, filter.startDate, filter.endDate);
    };

    useEffect(() => {
        fetchBookings();
    }, []);

    return (
        <div className="container mt-4">
            <h1 className="mb-4">Reservas</h1>

            <Form onSubmit={handleFilterSubmit} className="mb-3">
                <Form.Group className="mb-2">
                    <Form.Label>Género:</Form.Label>
                    <Form.Control
                        as="select"
                        name="genre"
                        value={filter.genre}
                        onChange={handleFilterChange}
                    >
                         <option value="">Seleccione un género</option>
                        {genres.map((genre) => (
                            <option key={genre} value={genre}>{genre}</option>
                        ))}
                    </Form.Control>
                </Form.Group>
                <Form.Group className="mb-2">
                    <Form.Label>Fecha de inicio:</Form.Label>
                    <Form.Control
                        type="date"
                        name="startDate"
                        value={filter.startDate}
                        onChange={handleFilterChange}
                    />
                </Form.Group>
                <Form.Group className="mb-2">
                    <Form.Label>Fecha de fin:</Form.Label>
                    <Form.Control
                        type="date"
                        name="endDate"
                        value={filter.endDate}
                        onChange={handleFilterChange}
                    />
                </Form.Group>
                <Button variant="primary" type="submit">Aplicar filtro</Button>
            </Form>

            <ul className="list-group mb-4">
    {bookings.map((booking) => (
        <li key={booking.id} className="list-group-item d-flex justify-content-between align-items-center">
            <div>
                <strong>Cliente:</strong> {booking.customer ? `${booking.customer.name} ${booking.customer.lastname}` : 'Desconocido'}
                <br />
                <small>Asiento: {booking.seat ? `${booking.seat.number}` : 'Desconocido'} - Sala: {booking.seat ? `${booking.seat.hallNumber}` : 'Desconocido'}</small>
                <br />
                <small>Cartelera: 
                    {booking.billboard && booking.billboard.movie ? `${booking.billboard.movie.name}` : 'Desconocido'}
                    {' ('}
                    {booking.billboard && booking.billboard.room ? `${booking.billboard.room.name}` : 'Desconocido'}
                    {')'}
                </small>
            </div>
            <div>
                <Button variant="info" size="sm" className="me-2" onClick={() => handleShowDetailModal(booking)}>Ver detalles</Button>
                <Button variant="danger" size="sm" onClick={() => handleShowConfirmationModal(booking)}>Cancelar</Button>
            </div>
        </li>
    ))}
</ul>

            <Button variant="primary" onClick={handleShowCreateModal}>Ingresar reserva</Button>

            <Modal show={showDetailModal} onHide={handleCloseDetailModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Detalles de la reserva</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                
                {booking && (
                    <>
                        <p><strong>ID:</strong> {booking.id}</p>
                        <p><strong>Estado:</strong> {booking.status ? 'Activo' : 'Inactivo'}</p>
                        <p><strong>Cliente:</strong> {booking.customer ? `${booking.customer.name} ${booking.customer.lastname}` : 'Desconocido'}</p>
                        <p><strong>Documento:</strong> {booking.customer ? booking.customer.documentNumber : 'Desconocido'}</p>
                        <p><strong>Teléfono:</strong> {booking.customer ? booking.customer.phoneNumber : 'Desconocido'}</p>
                        <p><strong>Email:</strong> {booking.customer ? booking.customer.email : 'Desconocido'}</p>
                        <p><strong>Asiento:</strong> {booking.seat ? booking.seat.number : 'Desconocido'}</p>
                        <p><strong>Hall:</strong> {booking.seat ? booking.seat.hallNumber : 'Desconocido'}</p>
                        <p><strong>Sala:</strong> {booking.seat && booking.seat.room ? booking.seat.room.name : 'Desconocido'}</p>
                        <p><strong>Pelicula:</strong> {booking.billboard && booking.billboard.movie ? booking.billboard.movie.name : 'Desconocido'}</p>
                        <p><strong>Fecha:</strong> {booking.billboard ? booking.billboard.date : 'Desconocido'}</p>
                        <p><strong>Hora de inicio:</strong> {booking.billboard ? booking.billboard.startTime : 'Desconocido'}</p>
                        <p><strong>Hora de fin:</strong> {booking.billboard ? booking.billboard.endTime : 'Desconocido'}</p>
                    </>
                )}

                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseDetailModal}>Cerrar</Button>
                </Modal.Footer>
            </Modal>
            <Modal show={showCreateModal} onHide={handleCloseCreateModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Ingresar reserva</Modal.Title>
                </Modal.Header>
                <Modal.Body>
    <Form onSubmit={(event) => {
        event.preventDefault();
        const newBooking = {
            date: event.target.date.value,
            customer: {
                id: event.target.customerId.value
            },
            seat: {
                id: event.target.seatId.value
            },
            billboard: {
                id: event.target.billboardId.value
            }
        };
        createBooking(newBooking);
        handleCloseCreateModal();
        event.target.reset();
    }}>
        <Form.Group className="mb-3">
            <Form.Label>ID del cliente:</Form.Label>
            <Form.Control type="text" name="customerId" required />
        </Form.Group>
        <Form.Group className="mb-3">
            <Form.Label>ID del asiento:</Form.Label>
            <Form.Control type="text" name="seatId" required />
        </Form.Group>
        <Form.Group className="mb-3">
            <Form.Label>ID de la cartelera:</Form.Label>
            <Form.Control type="text" name="billboardId" required />
        </Form.Group>
        <Form.Group className="mb-3">
            <Form.Label>Fecha de la reserva:</Form.Label>
            <Form.Control type="date" name="date" required />
        </Form.Group>
        <Button variant="primary" type="submit">Ingresar</Button>
    </Form>
</Modal.Body>

                <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseCreateModal}>Cerrar</Button>
                </Modal.Footer>
            </Modal>
            <Modal show={showConfirmationModal} onHide={handleCloseConfirmationModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Confirmación</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>¿Estás seguro de que deseas cancelar esta reserva?</p>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseConfirmationModal}>No</Button>
                    <Button variant="danger" onClick={handleConfirmCancel}>Sí</Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
};

export default BookingComponent;
