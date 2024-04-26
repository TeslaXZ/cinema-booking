import { Tab, Tabs } from 'react-tabs'; 
import Movies from "./Components/Movies";
import 'bootstrap/dist/css/bootstrap.min.css';
import Billboard from "./Components/Billboard"
import './App.css';

function App() {

  return (
    <div className="App">
      <h1>GuayaCine</h1>
      <Tabs>
        <Tab label="Peliculas">
          <Movies />
        </Tab>
        <Tab label="Cartelera">
          <Billboard />
        </Tab>
      </Tabs>
    </div>
  );
}

export default App
