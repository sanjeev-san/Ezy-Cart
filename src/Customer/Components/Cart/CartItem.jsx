import { Button, IconButton } from "@mui/material";
import React from "react";
import RemoveCircleOutlineIcon from "@mui/icons-material/RemoveCircleOutline";
import AddCircleOutlineIcon from "@mui/icons-material/AddCircleOutline";
const CartItem = () => {
  return (
    <div className="p-5 shadow-lg border rounded-md">
      <div className="flex items-center">
        <div className="w-[5rem] h-[5rem]  lg:w-[9rem] lg:h-[9rem]">
          <img
            src="https://indiansilkhouse.com/media/catalog/product/cache/a8725803dd87b79b16452131d8e7b0f3/2/2/22k_5158.jpg"
            alt=""
            className="w-full h-full object-cover object-top"
          />
        </div>
        <div className="ml-5 space-y-1">
          <p className="font-semibold"> Saree sale</p>
          <p className="opacity-70">Size : L, White</p>
          <p className="opacity-70 mt-2"> Seller : Brand name</p>
          <div className="flex space-x-5 items-center text-gray-900">
            <p className="font-semibold">199</p>
            <p className="opacity-50 line-through">221</p>
            <p className="text-green-600 font-semibold"> 5% off</p>
          </div>
        </div>
      </div>
      <div className="lg:flex items-center lg:space-x-10 pt-4">
          <div className="flex items-center space-x-2">
            <IconButton >
              <RemoveCircleOutlineIcon />
            </IconButton>
            <span className="py-1 px-7 border rounded-sm">2</span>
              <IconButton sx={{color:"RGB(145 85 253)"}}>
                <AddCircleOutlineIcon />
              </IconButton>   
          </div>
          <div>
            <Button sx={{color:"RGB(145 85 253)"}}>remove</Button>
          </div>
        </div>
    </div>
  );
};

export default CartItem;
