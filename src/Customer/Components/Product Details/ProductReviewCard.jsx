import { Avatar, Box, Grid } from "@mui/material";
import React from "react";

const ProductReviewCard = () => {
  return (
    <Grid container spacing={3} gap={3}>
      <Grid item xs={1}>
        <Box>
          <Avatar
            className="text-white "
            sx={{ width: 56, height: 56, bgcolor: "#9155fd" }}
          ></Avatar>
        </Box>
      </Grid>

      <Grid item xs={9}>

      </Grid>
    </Grid>
  );
};

export default ProductReviewCard;
