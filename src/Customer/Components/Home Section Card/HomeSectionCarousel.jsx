import React, { useState } from "react";
import AliceCarousel from "react-alice-carousel";
import HomeSectionCard from "./HomeSectionCard";
import { Button } from "@mui/material";
import KeyboardArrowRightIcon from "@mui/icons-material/KeyboardArrowRight";
import KeyboardArrowLeftIcon from "@mui/icons-material/KeyboardArrowLeft";
import { mens_kurta } from "../../Data/mens_kurta";
import "react-alice-carousel/lib/alice-carousel.css";

const HomeSectionCarousel = () => {
  const [activeIdx, setActiveIdx] = useState(0);
  const slidePrev = () => setActiveIdx(activeIdx - 1);
  const slideNext = () => setActiveIdx(activeIdx + 1);
  const syncActiveIdx = ({ item }) => setActiveIdx(item);

  const responsive = {
    0: { items: 1, itemsFit: "contain" },
    720: { items: 3, itemsFit: "contain" },
    1024: { items: 5.5, itemsFit: "contain" },
  };

  const items = mens_kurta
    ?.slice(0, 10)
    .map((item) => <HomeSectionCard product={item} />);

  return (
    <div className="relative px-4 lg:px-8">
      <div className="relative p-5">
        <AliceCarousel
          items={items}
          disableButtonsControls
          responsive={responsive}
          disableDotsControls
          activeIndex={activeIdx}
          onSlideChanged={syncActiveIdx}
        />

        <Button
          className="z-50"
          variant="contained"
          sx={{
            position: "absolute",
            top: "8rem",
            right: "0rem",
            transform: "",
            bgcolor: "white",
          }}
          aria-label="next"
          onClick={slideNext}
        >
          <KeyboardArrowRightIcon sx={{ color: "black" }} />
        </Button>

        <Button
          className="z-50"
          variant="contained"
          sx={{
            position: "absolute",
            top: "8rem",
            left: "0rem",
            transform: "",
            bgcolor: "white",
          }}
          aria-label="left"
          onClick={slidePrev}
        >
          <KeyboardArrowLeftIcon sx={{ color: "black" }} />
        </Button>
      </div>
    </div>
  );
};

export default HomeSectionCarousel;
