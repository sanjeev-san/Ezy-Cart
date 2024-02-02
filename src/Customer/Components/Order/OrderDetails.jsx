import React from "react";
import AddressCard from "../Checkout/AddressCard";
import OrderTracker from "./OrderTracker";
import { Box, Grid } from "@mui/material";
import { deepPurple } from "@mui/material/colors";
import StarBorderIcon from "@mui/icons-material/StarBorder";

const OrderDetails = () => {
  return (
    <div className="px:5 lg:px-20">
      <div className="">
        <h1 className=" font-bold text-lg py-7">Delivery Address</h1>
        <AddressCard />
      </div>
      <div className="py-20">
        <OrderTracker activeStep={3} />
      </div>
      <Grid container className="space-y-5">
       {[1,1,1,1,1,1].map((item)=> <Grid
          item
          container
          className="shadow-xl rounded-md p-5 border"
          sx={{ alignItems: "center", justifyContent: "space-between" }}
        >
          <Grid item xs={6}>
            <div className="flex items-center space-x-4">
              <img
                src="https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcTx98LSIOoCnCugQ4FXd-gfEM-bswcEjQ5CLzoxcgAX0vAvaVMbfRsy9vMsqcaJ8PcRYKqSPwLVz0JYauuNGkUt8r3svdJubAJ9fts3ZvUr4maaX29tbIsC1g"
                alt=""
                className="w-[5rem] h-[5rem] object-cover object-top"
              />
              <div className="space-y-2 ml-5 ">
                <p className="font-semibold ">Saree for women</p>
                <p className="space-x-5 opacity-50 text-xs font-semibold">
                  <span>Color : Pink</span>
                  <span>Size : M</span>
                </p>
                <p>Seller : MSP</p>
                <p>1099</p>
              </div>
            </div>
          </Grid>
          <Grid item>
            <Box sx={{ color: deepPurple[500] }}>
              <StarBorderIcon
                sx={{ fontSize: "2rem" }}
                className="px-2 text-5xl"
              />
              <span>Rate & Review Product</span>
            </Box>
          </Grid>
        </Grid>)}
      </Grid>
    </div>
  );
};

export default OrderDetails;
