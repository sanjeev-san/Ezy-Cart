import "./App.css";
import Footer from "./Customer/Components/Footer/Footer";
import Navigation from "./Customer/Components/Navigation/Navigation";
import ProductDetails from "./Customer/Components/Product Details/ProductDetails";
import Product from "./Customer/Components/Products/Product";
import Homepage from "./Customer/Pages/Homepage/Homepage";

function App() {
  return (
    <div className="">
      <Navigation />
      <div>
        {/* <Homepage /> */}
        {/* <Product /> */}
        <ProductDetails />
      </div>
      <div className="">
        <Footer />
      </div>
    </div>
  );
}

export default App;
