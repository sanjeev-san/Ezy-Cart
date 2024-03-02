import React from "react";
import { Route, Routes } from "react-router-dom";
import Homepage from "../Customer/Pages/Homepage/Homepage";
import Cart from "../Customer/Components/Cart/Cart";
import Navigation from "../Customer/Components/Navigation/Navigation";
import Footer from "../Customer/Components/Footer/Footer";
import Product from "../Customer/Components/Products/Product";
import ProductDetails from "../Customer/Components/Product Details/ProductDetails";
import Checkout from "../Customer/Components/Checkout/Checkout";
import Order from "../Customer/Components/Order/Order";
import OrderDetails from "../Customer/Components/Order/OrderDetails";

const CustomerRoute = () => {
  return (
    <div>
      <div className="">
        <Navigation />
      </div>
      <div className=""></div>
      <Routes>
        <Route path="/" element={<Homepage />}></Route>
        <Route path="/login" element={<Homepage />}></Route>
        <Route path="/signup" element={<Homepage />}></Route>
        
        <Route path="/cart" element={<Cart />}></Route>
        <Route
          path="/:levelOne/:levelTwo/:levelThree"
          element={<Product />}
        ></Route>
        <Route path="/product/:productId" element={<ProductDetails />}></Route>
        <Route path="/checkout" element={<Checkout />}></Route>
        <Route path="/account/order" element={<Order />}></Route>
        <Route
          path="/account/order/:orderId"
          element={<OrderDetails />}
        ></Route>
      </Routes>
      <div className="">
        <Footer />
      </div>
    </div>
  );
};

export default CustomerRoute;
