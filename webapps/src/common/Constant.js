export const USER = 'user';     /*localStore中用户信息 */
export const TOKEN = 'token';   /*localStore中令牌 */
export const DICTS = 'dicts';   /*localStore中字典 */
export const MENUS = 'menus';   /*localStore中菜单 */
export const BUTTONS = 'buttons';   /*localStore中菜单 */
export const serverUrl= process.env.NODE_ENV == "development" ? "ws://localhost:9999/" : "ws://192.168.0.112:9999/";
