import React from "react";
import Maincarousel from "../../Components/Home Carousel/Maincarousel";
import HomeSectionCarousel from "../../Components/Home Section Card/HomeSectionCarousel";
import "react-alice-carousel/lib/alice-carousel.css";
import { mens_kurta } from "../../Data/mens_kurta";
import Footer from "../../Components/Footer/Footer";

const Homepage = () => {
  return (
    <div>
      <Maincarousel />
      <div className="space-y-10 py-20 flex flex-col justify-center px-5 lg:px-10">
        <HomeSectionCarousel data={mens_kurta} sectionName={"Mens Kurta"} />
        <HomeSectionCarousel data={mens_kurta} sectionName={"Mens Shoes"} />
        <HomeSectionCarousel data={mens_kurta} sectionName={"Mens Shirt"} />
        <HomeSectionCarousel data={mens_kurta} sectionName={"women saree"} />
        <HomeSectionCarousel data={mens_kurta} sectionName={"women dress"} />
      </div>
    </div>
  );
};

export default Homepage;
