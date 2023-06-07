import { useEffect, useRef, useState } from 'react';
import { NavLink, useLocation } from 'react-router-dom';
import './SideBar.css';
import { BsPersonCircle, BsFileEarmarkPlusFill, BsFileEarmarkPlus, BsPlusCircleFill} from "react-icons/bs";

const sidebarNavItems = [
  {
    display: '회원목록',
    icon: <BsPersonCircle />,
    to: '/admin',
  },
  {
    display: '졸업요건 등록',
    icon: <BsFileEarmarkPlusFill/>,
    to: '/change',
  },
  {
    display: '강의 정보 등록',
    icon: <BsFileEarmarkPlus />,
    to: '/newsem',
  },
  {
    display: 'Test DataSet 생성',
    icon: <BsPlusCircleFill/>,
    to: '/dataset',
  },
];

const Sidebar = () => {
  const [activeIndex, setActiveIndex] = useState(0);
  const [stepHeight, setStepHeight] = useState(0);
  const sidebarRef = useRef();
  const colorRef = useRef();
  const location = useLocation();

  useEffect(() => {
    setTimeout(() => {
      const sidebarItem = sidebarRef.current.querySelector('.sidebar__menu__item');
      colorRef.current.style.color = `$red`;
      setStepHeight(sidebarItem.clientHeight);
    }, 50);
  }, []);

  useEffect(() => {
    const curPath = location.pathname;
    const activeItem = sidebarNavItems.findIndex(item => curPath.startsWith(item.to));
    setActiveIndex(activeItem);
  }, [location]);

  return (
    <div className='sidebar'>
      <div className="sidebar__logo">
      </div>
      <div ref={sidebarRef} className="sidebar__menu">
        {sidebarNavItems.map((item, index) => (
          <NavLink
            to={item.to}
            key={index}
            activeClassName="active" // Add 'active' class for the active item
          >
            <div className={`sidebar__menu__item`}>
              <div className="sidebar__menu__item__icon">
                {item.icon}
              </div>
              <div className="sidebar__menu__item__text" ref={colorRef}>
                {item.display}
              </div>
            </div>
          </NavLink>
        ))}
      </div>
    </div>
  );
};

export default Sidebar;
