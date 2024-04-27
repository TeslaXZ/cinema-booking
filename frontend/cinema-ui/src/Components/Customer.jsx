import { useState, useEffect } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Modal, Button, Form } from 'react-bootstrap';
import { apiBaseUrl } from '../ApiConfig';

const CustomerComponent = () => {
    const [customers, setCustomers] = useState([]);
    const [customer, setCustomer] = useState({});
    const [showDetailModal, setShowDetailModal] = useState(false);
    const [showCreateModal, setShowCreateModal] = useState(false);
    const [showConfirmationModal, setShowConfirmationModal] = useState(false);
    const [customerToDelete, setCustomerToDelete] = useState(null);

    const fetchCustomers = async () => {
        try {
            const response = await axios.get(`${apiBaseUrl}/customers`);
            setCustomers(response.data);
        } catch (error) {
            console.error('Error fetching customers:', error);
        }
    };


    const fetchCustomerById = async (id) => {
        try {
            const response = await axios.get(`${apiBaseUrl}/customers/${id}`);
            setCustomer(response.data);
            setShowDetailModal(true);
        } catch (error) {
            console.error('Error fetching customer by ID:', error);
        }
    };


    const createCustomer = async (newCustomer) => {
        try {
            const response = await axios.post(`${apiBaseUrl}/customers`, newCustomer);
            setCustomers([...customers, response.data]);
            fetchCustomers(); // Actualiza la lista de clientes
        } catch (error) {
            console.error('Error creating customer:', error);
        }
    };


    const updateCustomer = async (updatedCustomer) => {
        try {
            const response = await axios.put(`${apiBaseUrl}/customers`, updatedCustomer);
            const updatedCustomers = customers.map((customer) => {
                if (customer.id === response.data.id) {
                    return response.data;
                }
                return customer;
            });
            setCustomers(updatedCustomers);
        } catch (error) {
            console.error('Error updating customer:', error);
        }
    };

    const deleteCustomer = async (id) => {
        try {
            await axios.delete(`${apiBaseUrl}/customers/${id}`);
            setCustomers(customers.filter((customer) => customer.id !== id));
        } catch (error) {
            console.error('Error deleting customer:', error);
        }
    };

    // Controlar modales
    const handleShowDetailModal = (customer) => {
        setCustomer(customer);
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

    const handleShowConfirmationModal = (customer) => {
        setCustomerToDelete(customer);
        setShowConfirmationModal(true);
    };

    const handleCloseConfirmationModal = () => {
        setShowConfirmationModal(false);
        setCustomerToDelete(null);
    };

    const handleConfirmDelete = async () => {
        if (customerToDelete) {
            await deleteCustomer(customerToDelete.id);
            setCustomerToDelete(null);
            handleCloseConfirmationModal();
        }
    };

    useEffect(() => {
        fetchCustomers();
    }, []);

    return (
        <div className="container mt-4">
            <h1 className="mb-4">Clientes</h1>
            <ul className="list-group mb-4">
                {customers.map((customer) => (
                    <li key={customer.id} className="list-group-item d-flex justify-content-between align-items-center">
                        <div>
                            <strong>Nombre:</strong> {customer.name} {customer.lastname}
                            <br />
                            <small>Documento: {customer.documentNumber}</small>
                            <br />
                            <small>Teléfono: {customer.phoneNumber}</small>
                            <br />
                            <small>Email: {customer.email}</small>
                        </div>
                        <div>
                            <Button variant="info" size="sm" className="me-2" onClick={() => handleShowDetailModal(customer)}>Ver detalles</Button>
                            <Button variant="danger" size="sm" onClick={() => handleShowConfirmationModal(customer)}>Eliminar</Button>
                        </div>
                    </li>
                ))}
            </ul>
            <Button variant="primary" onClick={handleShowCreateModal}>Ingresar cliente</Button>
            <Modal show={showDetailModal} onHide={handleCloseDetailModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Detalles de cliente</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    {customer && (
                        <>
                            <p><strong>ID:</strong> {customer.id}</p>
                            <p><strong>Nombre:</strong> {customer.name}</p>
                            <p><strong>Apellido:</strong> {customer.lastname}</p>
                            <p><strong>Documento:</strong> {customer.documentNumber}</p>
                            <p><strong>Teléfono:</strong> {customer.phoneNumber}</p>
                            <p><strong>Email:</strong> {customer.email}</p>
                            <p><strong>Edad:</strong> {customer.age}</p>
                        </>
                    )}
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseDetailModal}>Cerrar</Button>
                </Modal.Footer>
            </Modal>
            <Modal show={showCreateModal} onHide={handleCloseCreateModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Ingresar cliente</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form onSubmit={(event) => {
                        event.preventDefault();
                        const newCustomer = {
                            documentNumber: event.target.documentNumber.value,
                            name: event.target.name.value,
                            lastname: event.target.lastname.value,
                            age: parseInt(event.target.age.value),
                            phoneNumber: event.target.phoneNumber.value,
                            email: event.target.email.value
                        };
                        createCustomer(newCustomer);
                        handleCloseCreateModal();
                        event.target.reset();
                    }}>
                        <Form.Group className="mb-3">
                            <Form.Label>Número de documento:</Form.Label>
                            <Form.Control type="text" name="documentNumber" required />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Nombre:</Form.Label>
                            <Form.Control type="text" name="name" required />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Apellido:</Form.Label>
                            <Form.Control type="text" name="lastname" required />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Edad:</Form.Label>
                            <Form.Control type="number" name="age" required />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Teléfono:</Form.Label>
                            <Form.Control type="text" name="phoneNumber" />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Email:</Form.Label>
                            <Form.Control type="email" name="email" />
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
                    <p>¿Estás seguro de que deseas eliminar este cliente?</p>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseConfirmationModal}>No</Button>
                    <Button variant="danger" onClick={handleConfirmDelete}>Sí</Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
};

export default CustomerComponent;
