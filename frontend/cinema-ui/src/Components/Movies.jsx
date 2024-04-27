import { useState, useEffect } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Modal, Button, Form } from 'react-bootstrap';
import { apiBaseUrl } from '../ApiConfig';

const MovieComponent = () => {
    const [movies, setMovies] = useState([]);
    const [movie, setMovie] = useState({});
    const [showDetailModal, setShowDetailModal] = useState(false);
    const [showCreateModal, setShowCreateModal] = useState(false);

    const fetchMovies = async () => {
        try {
            const response = await axios.get(`${apiBaseUrl}/movie`);
            const filteredMovies = response.data.filter(movie => movie.status);
            setMovies(filteredMovies);
        } catch (error) {
            console.error('Error fetching movies:', error);
        }
    };


    const fetchMovieById = async (id) => {
        try {
            const response = await axios.get(`${apiBaseUrl}/movie/${id}`);
            setMovie(response.data);
            setShowDetailModal(true);
        } catch (error) {
            console.error('Error fetching movie by ID:', error);
        }
    };

    const createMovie = async (newMovie) => {
        try {
            const response = await axios.post(`${apiBaseUrl}/movie`, newMovie);
            setMovies([...movies, response.data]);
            fetchMovies();
        } catch (error) {
            console.error('Error creating movie:', error);
        }
    };

 
    const deleteMovie = async (id) => {
        try {
            await axios.delete(`${apiBaseUrl}/movie/${id}`);
            setMovies(movies.filter((movie) => movie.id !== id));
        } catch (error) {
            console.error('Error deleting movie:', error);
        }
    };

    const handleShowDetailModal = (movie) => {
        setMovie(movie);
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

    useEffect(() => {
        fetchMovies();
    }, []);

    return (
        <div className="container mt-4">
            <h1 className="mb-4">Películas</h1>

            <ul className="list-group mb-4">
                {movies.map((movie) => (
                    <li key={movie.id} className="list-group-item d-flex justify-content-between align-items-center">
                        <div>
                            <strong>{movie.name}</strong> ({movie.genre})
                        </div>
                        <div>
                            <Button variant="info" size="sm" className="me-2" onClick={() => handleShowDetailModal(movie)}>Ver detalles</Button>
                            <Button variant="danger" size="sm" onClick={() => deleteMovie(movie.id)}>Eliminar</Button>
                        </div>
                    </li>
                ))}
            </ul>

        
            <Button variant="primary" onClick={handleShowCreateModal}>Ingresar película</Button>

            
            <Modal show={showDetailModal} onHide={handleCloseDetailModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Detalles de la película</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    {movie && (
                        <>
                            <p><strong>ID:</strong> {movie.id}</p>
                            <p><strong>Nombre:</strong> {movie.name}</p>
                            <p><strong>Género:</strong> {movie.genre}</p>
                            <p><strong>Edad permitida:</strong> {movie.allowedAge}</p>
                            <p><strong>Duración (minutos):</strong> {movie.lengthMinutes}</p>
                            <p><strong>Estado:</strong> {movie.status ? 'Activo' : 'Inactivo'}</p>
                        </>
                    )}
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseDetailModal}>Cerrar</Button>
                </Modal.Footer>
            </Modal>

            
            <Modal show={showCreateModal} onHide={handleCloseCreateModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Ingresar película</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form onSubmit={(event) => {
                        event.preventDefault();
                        const newMovie = {
                            name: event.target.name.value,
                            genre: event.target.genre.value,
                            allowedAge: event.target.allowedAge.value,
                            lengthMinutes: event.target.lengthMinutes.value,
                        };
                        createMovie(newMovie);
                        handleCloseCreateModal();
                        event.target.reset();
                    }}>
                        <Form.Group className="mb-3">
                            <Form.Label>Nombre:</Form.Label>
                            <Form.Control type="text" name="name" required />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Género:</Form.Label>
                            <Form.Control type="text" name="genre" required />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Edad permitida:</Form.Label>
                            <Form.Control type="number" name="allowedAge" required />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Duración (minutos):</Form.Label>
                            <Form.Control type="number" name="lengthMinutes" required />
                        </Form.Group>
                        <Button variant="primary" type="submit">Ingresar</Button>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseCreateModal}>Cerrar</Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
};

export default MovieComponent;
