import { Outlet } from 'react-router-dom'; 
// Path: twitter_front/src/components/layout.tsx

// Compare this snippet from twitter_front/src/main.tsx:
export default function Layout() {
  return ( // <Layout />을 호출하면 <Outlet />을 호출하게 됨
    <>
        <h2>Layout</h2> //네비게이션 바
        <Outlet />
    </>
  );
}