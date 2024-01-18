import React from "react";
import Maincarousel from "../../Components/Home Carousel/Maincarousel";
import HomeSectionCarousel from "../../Components/Home Section Card/HomeSectionCarousel";
import "react-alice-carousel/lib/alice-carousel.css";

const Homepage = () => {
  return (
    <div>
      <Maincarousel />

      <div>
        <HomeSectionCarousel />
      </div>
    </div>
  );
};

export default Homepage;
