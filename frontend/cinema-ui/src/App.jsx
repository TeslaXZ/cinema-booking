import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';
import 'react-tabs/style/react-tabs.css'; // Asegúrate de importar los estilos de react-tabs
import Movies from './Components/Movies';
import Billboard from './Components/Billboard';
import Booking from './Components/Booking'
import Customer from './Components/Customer'
import Room from './Components/Room'
import './App.css';

function App() {
    return (
        <div className="App">
            <h1>GuayaCine</h1>
            <Tabs>
                <TabList>
                    <Tab>Películas</Tab>
                    <Tab>Cartelera</Tab>
                    <Tab>Reservas</Tab>
                    <Tab>Clientes</Tab>
                    <Tab>Salas</Tab>
                </TabList>
                <TabPanel>
                    <Movies />
                </TabPanel>
                <TabPanel>
                    <Billboard />
                </TabPanel>
                <TabPanel>
                    <Booking />
                </TabPanel>
                <TabPanel>
                    <Customer />
                </TabPanel>
                <TabPanel>
                    <Room />
                </TabPanel>
            </Tabs>
        </div>
    );
}

export default App;

