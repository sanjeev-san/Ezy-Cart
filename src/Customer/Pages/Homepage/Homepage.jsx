import React from "react";
import Maincarousel from "../../Components/Home Carousel/Maincarousel";
import HomeSectionCarousel from "../../Components/Home Section Card/HomeSectionCarousel";
import "react-alice-carousel/lib/alice-carousel.css";

const Homepage = () => {
  return (
    <div>
      <Maincarousel />

      <div className="space-y-10 py-20 flex flex-col justify-center">
        <HomeSectionCarousel />
        <HomeSectionCarousel />
        <HomeSectionCarousel />
        <HomeSectionCarousel />
        <HomeSectionCarousel />
        <HomeSectionCarousel />
        <HomeSectionCarousel />
      </div>
    </div>
  );
};

export default Homepage;
