import React from 'react'

const HomeSectionCard = () => {
  return (
    <div className='cursor-pointer flex flex-col items-center bg-white rounded-lg shadow-lg overflow-hidden w-[15rem] mx-3'>
      

        <div className='h-[13-rem] w-[10rem]'> 
            <img src="https://assets.ajio.com/medias/sys_master/root/20231205/G3z0/656ed440ddf7791519b1e6e2/-473Wx593H-461119105-blue-MODEL.jpg"  className='object-cover object-top w-full h-full' alt="" />
        </div>

        <div className='p-4'>
            <h3 className='text-lg font-medium text-gray-900'>Company</h3>
            <p className='mt-2 text-sm text-gray-500'>Mens solid cotton Shirt</p>
        </div>

    </div>
  )
}

export default HomeSectionCard
