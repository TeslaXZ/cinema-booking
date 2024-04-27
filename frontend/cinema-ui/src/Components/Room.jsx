import { useState, useEffect } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Table, Modal, Button } from 'react-bootstrap';
import { apiBaseUrl } from '../ApiConfig';

const RoomComponent = () => {
    const [rooms, setRooms] = useState([]);
    const [selectedRoom, setSelectedRoom] = useState(null);
    const [showDetailModal, setShowDetailModal] = useState(false);

    // Obtener la disponibilidad de asientos en las salas
    const fetchRooms = async () => {
        try {
            const response = await axios.get(`${apiBaseUrl}/rooms/seatsAvailability`);
            setRooms(response.data);
        } catch (error) {
            console.error('Error fetching rooms:', error);
        }
    };

    const fetchRoomById = async (roomId) => {
        try {
            const response = await axios.get(`${apiBaseUrl}/rooms/${roomId}`);
            setSelectedRoom(response.data);
            setShowDetailModal(true);
        } catch (error) {
            console.error(`Error fetching room details for room ${roomId}:`, error);
        }
    };
    const handleCloseDetailModal = () => {
        setShowDetailModal(false);
        setSelectedRoom(null);
    };

    useEffect(() => {
        fetchRooms();
    }, []);

    return (
        <div className="container mt-4">
            <h1 className="mb-4">Salas y disponibilidad de asientos</h1>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Nombre de la sala</th>
                        <th>Asientos disponibles</th>
                        <th>Asientos ocupados</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {rooms.map((room, index) => (
                        <tr key={index}>
                            <td>{room.roomName}</td>
                            <td>{room.availableSeats}</td>
                            <td>{room.occupiedSeats}</td>
                            <td>
                                <Button
                                    variant="info"
                                    size="sm"
                                    onClick={() => fetchRoomById(room.id)}
                                >
                                    Ver detalles
                                </Button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>
            <Modal show={showDetailModal} onHide={handleCloseDetailModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Detalles de la sala</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    {selectedRoom && (
                        <>   <p><strong>Id:</strong> {selectedRoom.id}</p>
                            <p><strong>Nombre de la sala:</strong> {selectedRoom.name}</p>
                            <p><strong>Numero:</strong> {selectedRoom.number}</p>
                            
                            
                        </>
                    )}
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseDetailModal}>Cerrar</Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
};

export default RoomComponent;
