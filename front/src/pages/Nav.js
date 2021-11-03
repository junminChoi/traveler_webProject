//pages/Nav.js
import React from "react";
import { Link } from "react-router-dom";

function Nav() {
  return (
    <>
      <Link to="/">
        <button>메인 화면으로</button>
      </Link>
      <Link to="/login">
        <button>로그인 화면으로</button>
      </Link>
      <Link to="/info">
        <button>정보 화면으로</button>
      </Link>
    </>
  );
}

export default Nav;