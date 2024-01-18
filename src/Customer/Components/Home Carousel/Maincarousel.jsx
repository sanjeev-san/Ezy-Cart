import React from "react";
import AliceCarousel from "react-alice-carousel";
import "react-alice-carousel/lib/alice-carousel.css";
import { MaincarouselData } from "./MaincarouselData";
import "react-alice-carousel/lib/alice-carousel.css";

const Maincarousel = () => {
  const items = MaincarouselData.map((item) => (
    <img
      className="cursor-pointer"
      role="presentation"
      src={item.image}
      alt=""
    />
  ));

  return <AliceCarousel items={items} autoPlay autoPlayInterval={1500} disableButtonsControls infinite/>;
};
export default Maincarousel;
