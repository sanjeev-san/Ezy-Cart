import React from "react";
import AliceCarousel from "react-alice-carousel";
import HomeSectionCard from "./HomeSectionCard";
import { Button } from "@mui/material";
import KeyboardArrowRightIcon from "@mui/icons-material/KeyboardArrowRight";
import KeyboardArrowLeftIcon from "@mui/icons-material/KeyboardArrowLeft";
import { mens_kurta } from "../../Data/mens_kurta";
import "react-alice-carousel/lib/alice-carousel.css";

const HomeSectionCarousel = () => {
  const renderNextButton = () => {
    return (
      <Button
        className="z-50 bg-[]"
        variant="contained"
        sx={{
          position: "absolute",
          top: "8rem",
          right: "0rem",
          transform: "",
          bgcolor: "white",
        }}
        aria-label="next"
      >
        <KeyboardArrowRightIcon sx={{ color: "black" }} />
      </Button>
    );
  };

  const renderPrevButton = () => {
    return (
      <Button
        className="z-50 bg-[]"
        variant="contained"
        sx={{
          position: "absolute",
          top: "8rem",
          left: "0rem",
          transform: "",
          bgcolor: "white",
        }}
        aria-label="prev"
      >
        <KeyboardArrowLeftIcon sx={{ color: "black" }} />
      </Button>
    );
  };

  const responsive = {
    0: { items: 2, itemsFit: "contain" },
    568: { items: 3, itemsFit: "contain" },
    1024: { items: 5.5, itemsFit: "contain" },
  };

  const items = mens_kurta
    ?.slice(0, 10)
    .map((item) => <HomeSectionCard product={item} />);

  return (
    <div className="relative px-4 lg:px-8 sm:px-6">

      <div className="relative p-5 border">
        <AliceCarousel
          mouseTracking
          disableDotsControls
          items={items}
          responsive={responsive}
          controlsStrategy="responsive"
          renderPrevButton={renderPrevButton}
          renderNextButton={renderNextButton}
        />
      </div>
    </div>
  );
};

export default HomeSectionCarousel;
