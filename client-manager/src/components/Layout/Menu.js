import React, { PropTypes } from 'react'
import { Menu, Icon } from 'antd'
import { Link } from 'dva/router'

// const topMenus = menu.map(item => item.id)
let topMenus = []
const getMenus = function (menuArray, siderFold, parentPath = '/') {
  return menuArray.map(item => {
    // item.icon = 'bars'
    const linkTo = parentPath + item.code
    if (item.children) {
      return (
        <Menu.SubMenu key={linkTo} title={<span>{item.icon ? <Icon type={item.icon} /> : ''}{siderFold && topMenus.indexOf(item.id) >= 0 ? '' : item.name}</span>}>
          {getMenus(item.children, siderFold, `${linkTo}/`)}
        </Menu.SubMenu>
      )
    }
    return (
      <Menu.Item key={linkTo}>
        <Link to={linkTo}>
          {item.icon ? <Icon type={item.icon} /> : ''}
          {siderFold && topMenus.indexOf(item.id) >= 0 ? '' : item.name}
        </Link>
      </Menu.Item>
    )
  })
}

function Menus ({ loginUser, siderFold, darkTheme, location, handleClickNavMenu, navOpenKeys, changeOpenKeys }) {
  topMenus = loginUser.permissionList.map(item => item.id)
  const menuItems = getMenus(loginUser.permissionList, siderFold)

  const getAncestorKeys = (key) => {
    const map = {
      '/navigation/navigation2': ['/navigation'],
    }
    return map[key] || []
  }

  const onOpenChange = (openKeys) => {
    const latestOpenKey = openKeys.find(key => !(navOpenKeys.indexOf(key) > -1))
    const latestCloseKey = navOpenKeys.find(key => !(openKeys.indexOf(key) > -1))
    let nextOpenKeys = []
    if (latestOpenKey) {
      nextOpenKeys = getAncestorKeys(latestOpenKey).concat(latestOpenKey)
    }
    if (latestCloseKey) {
      nextOpenKeys = getAncestorKeys(latestCloseKey)
    }
    changeOpenKeys(nextOpenKeys)
  }

  let menuProps = !siderFold ? {
    onOpenChange,
    openKeys: navOpenKeys,
  } : {}

  return (
    <Menu
      {...menuProps}
      mode={siderFold ? 'vertical' : 'inline'}
      theme={darkTheme ? 'dark' : 'light'}
      onClick={handleClickNavMenu}
      defaultSelectedKeys={[location.pathname !== '/' ? location.pathname : '/dashboard']}
    >
      {menuItems}
    </Menu>
  )
}

Menus.propTypes = {
  siderFold: PropTypes.bool,
  darkTheme: PropTypes.bool,
  location: PropTypes.object,
  loginUser: PropTypes.object,
  isNavbar: PropTypes.bool,
  handleClickNavMenu: PropTypes.func,
  navOpenKeys: PropTypes.array,
  changeOpenKeys: PropTypes.func,
}

export default Menus
