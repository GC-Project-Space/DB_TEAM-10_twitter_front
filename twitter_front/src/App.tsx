import { RouterProvider, createBrowserRouter } from "react-router-dom";
import Layout from './components/Layout';
import Home from './routes/home';
import Profile from './routes/profile';
import reset from 'styled-reset';
import { createGlobalStyle, styled } from 'styled-components';
import Login from "./routes/login";
import CreateAccount from "./routes/create-account";

const router= createBrowserRouter([
  {
    path: "/", // localhost:5173
    element: <Layout />, // Layout으로 감싸진 Outlet이 호출됨
    children: [ // Layout의 Outlet에는 다음과 같은 컴포넌트들이 호출될 수 있음
      { //Layout을 감싸짐 (children은 Layout의 요소가 됨)
        // = Layout component에서 reder가 됨
        path: "", 
        element: <Home />, 
      },
      {
        path: "profile",
        element: <Profile />,
      },
    ],
  },
  //Layout으로 감싸지게 하지 않음
  {
    path: "/login",
    element: <Login/>
  },
  {
    path: "/create-account",
    element: <CreateAccount/>
  }
]);

const Globalstyles = createGlobalStyle`
  ${reset};
  *{
    box-sizing: border-box;
  }
  body{
    background-color: #black;
    color: white;
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI'
    , Roboto, Ubuntu, 'Helvetica Neue', sans-seri;
  }
`;

function App() {
  return(
    <>
      <Globalstyles/>
      <RouterProvider router={router}/> //router를 전달
    </>
  );
}

export default App;
