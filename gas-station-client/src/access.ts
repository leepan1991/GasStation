/**
 * @see https://umijs.org/zh-CN/plugins/plugin-access
 * */
import {LoginInfo} from "@/define/data";

export default function access(initialState: { currentUser?: LoginInfo | undefined }) {
  const {currentUser} = initialState || {};
  return {
    normalRouteFilter: (route: any) => {
      if (!currentUser) {
        return false;
      }
      return currentUser?.resourceCodeList.indexOf(route.auth || route.name) > -1;
    }
  };
}
