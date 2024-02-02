import { Route, Routes } from "react-router-dom";
import "./App.css";
import Cart from "./Customer/Components/Cart/Cart";
import Checkout from "./Customer/Components/Checkout/Checkout";
import Footer from "./Customer/Components/Footer/Footer";
import Navigation from "./Customer/Components/Navigation/Navigation";
import Order from "./Customer/Components/Order/Order";
import OrderDetails from "./Customer/Components/Order/OrderDetails";
import ProductDetails from "./Customer/Components/Product Details/ProductDetails";
import Product from "./Customer/Components/Products/Product";
import Homepage from "./Customer/Pages/Homepage/Homepage";
import CustomerRoute from "./Routers/CustomerRoute";

function App() {
  return (
    <div className="">
      <Routes>
        <Route path="/*" element={<CustomerRoute />} />
      </Routes>
    </div>
  );
}

export default App;
