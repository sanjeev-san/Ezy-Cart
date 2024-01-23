import React from "react";
import AliceCarousel from "react-alice-carousel";
import HomeSectionCard from "./HomeSectionCard";
import { Button } from "@mui/material";
import KeyboardArrowRightIcon from "@mui/icons-material/KeyboardArrowRight";
import KeyboardArrowLeftIcon from "@mui/icons-material/KeyboardArrowLeft";
import "react-alice-carousel/lib/alice-carousel.css";

const HomeSectionCarousel = ({ data, sectionName }) => {
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
    1024: { items: 5, itemsFit: "contain" },
  };

  const items = data
    ?.slice(0, 10)
    .map((item) => <HomeSectionCard product={item} />);

  return (
    <div className="bg-white">
      <div className="mx-auto max-w-2xl px-4 lg:max-w-7xl lg:px-8">
        <h2 className="text-2xl font-bold tracking-tight text-gray-900 my-2">
          {sectionName}
        </h2>
        <AliceCarousel
          mouseTracking
          disableDotsControls
          items={items}
          responsive={responsive}
          controlsStrategy="responsive"
          renderPrevButton={renderPrevButton}
          renderNextButton={renderNextButton}
          className="mt-6 grid grid-cols-1 gap-x-6 gap-y-10 sm:grid-cols-2 lg:grid-cols-4 xl:gap-x-8"
        />
      </div>
    </div>
  );
};

export default HomeSectionCarousel;
