// MenuBar.js
import React from 'react';
import { List, ListItem, Button, Drawer } from '@material-ui/core';
import clsx from 'clsx';
import { NavLink as RouterLink } from 'react-router-dom';
import useStyles from './MenuBarStyle';

const MenuBar = (props) => {
  const { className, ...rest } = props;
  const classes = useStyles();
  const menus = [
      { "name": "회원목록", "url": "/admin" },
      { "name": "졸업요건 변경", "url": "/change" },
      { "name": "강의 정보 등록", "url": "/newsem" },
      { "name": "Test DataSet 생성", "url": "/dataset" }
  ];

  const CustomRouterLink = React.forwardRef((props, ref) => (
    <div ref={ref} style={{ flexGrow: 1 }}>
      <RouterLink {...props} />
    </div>
  ));

  const handleMenu = (items) => {
    return items.map(({ name, url }) => {
      return (
        <List component="div" disablePadding key={name}>
          <ListItem
            className={classes.item}
            disableGutters
            style={{ padding: '0px' }}
            key={name}
          >
            <Button
              className={clsx({
                [classes.btnRoot]: true,
                [classes.button]: true,
              })}
              component={CustomRouterLink}
              to={url}
            >
              {name}
            </Button>
          </ListItem>
        </List>
      );
    });
  };

  return (
    <Drawer
      anchor="left"
      classes={{
        paper: clsx(classes.drawer, classes.sidebarBackground)
      }}
      open={true}
      variant="persistent"
    >
      <List {...rest} className={clsx(classes.root, className)}>
        {handleMenu(menus)}
      </List>
    </Drawer>
  );
};

export default MenuBar;