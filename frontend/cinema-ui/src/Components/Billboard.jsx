import { useState, useEffect } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Modal, Button, Form } from 'react-bootstrap';
import { apiBaseUrl } from '../ApiConfig';

const BillboardComponent = () => {
    const [billboards, setBillboards] = useState([]);
    const [billboard, setBillboard] = useState({});
    const [showDetailModal, setShowDetailModal] = useState(false);
    const [showCreateModal, setShowCreateModal] = useState(false);
    const [showConfirmationModal, setShowConfirmationModal] = useState(false);
    const [billboardToCancel, setBillboardToCancel] = useState(null);

    

    
    const fetchBillboards = async () => {
        try {
            const response = await axios.get(`${apiBaseUrl}/billboard`);
            const filteredBillboards = response.data.filter(billboard => billboard.status);
            setBillboards(filteredBillboards);
        } catch (error) {
            console.error('Error fetching billboards:', error);
        }
    };

   
    const fetchBillboardById = async (id) => {
        try {
            const response = await axios.get(`${apiBaseUrl}/billboard/${id}`);
            setBillboard(response.data);
            setShowDetailModal(true);
        } catch (error) {
            console.error('Error fetching billboard by ID:', error);
        }
    };


    const createBillboard = async (newBillboard) => {
        try {
            const response = await axios.post(`${apiBaseUrl}/billboard`, newBillboard);
            setBillboards([...billboards, response.data]);
            fetchBillboards();
        } catch (error) {
            console.error('Error creating billboard:', error);
        }
    };

    
    const cancelBillboard = async (id) => {
        try {
            await axios.delete(`${apiBaseUrl}/billboard/cancelBillboardAndBookings?id=${id}`);
            setBillboards(billboards.filter((billboard) => billboard.id !== id));
        } catch (error) {
            console.error('Error canceling billboard:', error);
        }
    };

    const handleShowDetailModal = (billboard) => {
        setBillboard(billboard);
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

    const handleShowConfirmationModal = (billboard) => {
        setBillboardToCancel(billboard);
        setShowConfirmationModal(true);
    };

    const handleCloseConfirmationModal = () => {
        setShowConfirmationModal(false);
        setBillboardToCancel(null);
    };

    const handleConfirmCancel = async () => {
        if (billboardToCancel) {
            await cancelBillboard(billboardToCancel.id);
            setBillboardToCancel(null);
            handleCloseConfirmationModal();
        }
    };

    useEffect(() => {
        fetchBillboards();
    }, []);

    return (
        <div className="container mt-4">
            <h1 className="mb-4">Carteleras</h1>
            <ul className="list-group mb-4">
            {billboards.map((billboard) => (
                <li key={billboard.id} className="list-group-item d-flex justify-content-between align-items-center">
                    <div>
                        <strong>
                            {billboard.movie && billboard.movie.name ? billboard.movie.name : 'Desconocido'}
                        </strong>
                        {' ('}
                        {billboard.room && billboard.room.name ? billboard.room.name : 'Desconocido'}
                        {')'}
                        <br />
                        <small>Fecha: {billboard.date} - Hora: {billboard.startTime} a {billboard.endTime}</small>
                    </div>
                    <div>
                        <Button variant="info" size="sm" className="me-2" onClick={() => handleShowDetailModal(billboard)}>Ver detalles</Button>
                        <Button variant="danger" size="sm" onClick={() => handleShowConfirmationModal(billboard)}>Cancelar</Button>
                    </div>
                </li>
            ))}

            </ul>

            <Button variant="primary" onClick={handleShowCreateModal}>Ingresar cartelera</Button>
            <Modal show={showDetailModal} onHide={handleCloseDetailModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Detalles de la cartelera</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                {billboard && (
                     <>
                        <p><strong>ID:</strong> {billboard.id}</p>
                        <p><strong>Fecha:</strong> {billboard.date}</p>
                        <p><strong>Hora de inicio:</strong> {billboard.startTime}</p>
                        <p><strong>Hora de fin:</strong> {billboard.endTime}</p>
                        <p><strong>Película:</strong> {billboard.movie && billboard.movie.name}</p>
                        <p><strong>Género:</strong> {billboard.movie && billboard.movie.genre}</p>
                        <p><strong>Duración (minutos):</strong> {billboard.movie && billboard.movie.lengthMinutes}</p>
                        <p><strong>Sala:</strong> {billboard.room && billboard.room.name}</p>
                    </>
                )}

                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseDetailModal}>Cerrar</Button>
                </Modal.Footer>
            </Modal>
            <Modal show={showCreateModal} onHide={handleCloseCreateModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Ingresar cartelera</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form onSubmit={(event) => {
                        event.preventDefault();
                        const newBillboard = {
                            date: event.target.date.value,
                            startTime: event.target.startTime.value,
                            endTime: event.target.endTime.value,
                            movie: {
                                id: event.target.movieId.value
                            },
                            room: {
                                id: event.target.roomId.value
                            }
                        };
                        createBillboard(newBillboard);
                        handleCloseCreateModal();
                        event.target.reset();
                    }}>
                        <Form.Group className="mb-3">
                            <Form.Label>Fecha:</Form.Label>
                            <Form.Control type="date" name="date" required />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Hora de inicio:</Form.Label>
                            <Form.Control type="time" name="startTime" required />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Hora de fin:</Form.Label>
                            <Form.Control type="time" name="endTime" required />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>ID de la película:</Form.Label>
                            <Form.Control type="text" name="movieId" required />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>ID de la sala:</Form.Label>
                            <Form.Control type="text" name="roomId" required />
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
                    <p>¿Estás seguro de que deseas cancelar esta cartelera?</p>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseConfirmationModal}>No</Button>
                    <Button variant="danger" onClick={handleConfirmCancel}>Sí</Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
};

export default BillboardComponent;
