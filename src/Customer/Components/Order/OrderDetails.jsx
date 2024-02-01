import React from "react";
import AddressCard from "../Checkout/AddressCard";

const OrderDetails = () => {
  return (
    <div className="px:5 lg:px-20">
      <div className="">
        <h1 className=" font-bold text-lg py-10">Delivery Address</h1>
        <AddressCard />
      </div>
    </div>
  );
};

export default OrderDetails;
