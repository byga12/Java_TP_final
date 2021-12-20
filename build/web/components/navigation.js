class navigation extends HTMLElement {
  constructor() {
    super();
  }
  connectedCallback() {
    this.innerHTML = `
      <style>
      body::-webkit-scrollbar {
        width: 12px;               /* width of the entire scrollbar */
      }
      
      body::-webkit-scrollbar-track {
        background: transparent;        /* color of the tracking area */
      }
      
      body::-webkit-scrollbar-thumb {
        background-color: #4b535c;    /* color of the scroll thumb */
        border-radius: 20px;       /* roundness of the scroll thumb */
        border: 3px solid white;  /* creates padding around scroll thumb */
      }
      .nav_container {
        width: 15rem;
        flex-shrink: 0;
        background-color: #38373E;
        position: sticky;
        top: 0;
        left: 0;
        height: 100vh;
        display: flex;
        flex-direction: column;
        gap: 1rem;
        overflow-y: scroll;
      }
      
      /* LOGO */
      .logo {
        color: white;
        margin-top: 2.3rem;
        margin-bottom: 1.7rem;
        text-align: center;
      }
      
      /* SECTION */
      .section_list {
        cursor: pointer;
        transition: background-color .2s ease-in;
        border-radius: 10px;
      
      }
      
      .section_title {
        color: #59575F;
        padding-block: .7rem;
        padding-left: 1rem;
        font-size: 1.1rem;
      }
      
      .section_item {
        padding-block: .6rem;
        padding-inline: 1rem;
        display: flex;
        gap: 1.2rem;
        align-items: center;
      
      }
      
      .section_item:hover {
        background-color: #36353C
      }
      
      .section_item_icon {
        background-color: #525158;
        color: #8F8E96;
        width: 35px;
        height: 35px;
        border-radius: 5px;
        display: flex;
        justify-content: center;
        align-items: center;
      }
      
      .section_item_text {
        font-size: 1.2rem;
        color: #717077;
        font-weight: 300;
      }

      .logout_button {
        border: none;
        background-color: transparent;
        display: block;
        margin: 0 auto;
        cursor: pointer;
        margin-bottom: 1rem;
      }

      .logout_icon {
        color: #8F8E96;
        font-size: 1.7rem;
      }
          
      </style>
      <nav class="nav_container">

        <h1 class="logo">tourA</h1>

        <section class="section">
        <h2 class="section_title">General</h2>

        <ul class="section_list">
          <li>
            <a href="../homePage/home.jsp" class="section_item">
              <i class="fas fa-home section_item_icon"></i>
              <p class="section_item_text">Inicio</p>
            </a>
          </li>
          <li>
            <a href="../../SvTouristService" class="section_item">
              <i class="fas fa-clipboard-list section_item_icon"></i>
              <p class="section_item_text">Servicios</p>
            </a>
          </li>
          <li>
            <a href="../../SvTouristPackage" class="section_item">
              <i class="fas fa-cubes section_item_icon"></i>
              <p class="section_item_text">Paquetes</p>
            </a>
          </li>
          <li>
            <a href="../../SvSale" class="section_item">
              <i class="fas fa-money-bill-wave section_item_icon"></i>
              <p class="section_item_text">Ventas</p>
            </a>
          </li>
          
        </ul>
      </section>
      <section class="section">
        <h2 class="section_title">Usuarios</h2>

        <ul class="section_list">

            <li>
              <a href="../../SvEmployee" class="section_item">
                <i class="fas fa-users-cog section_item_icon"></i>
                <p class="section_item_text">Empleados</p>
              </a>
            </li>
 
          <li>
            <a href="../../SvCustomer" class="section_item">
              <i class="fas fa-users section_item_icon"></i>
              <p class="section_item_text">Clientes</p>
            </a>
          </li>
        </ul>
      </section>

      <form class="section" action="../../SvLogout" method="POST">
        <button class="logout_button" type="submit"><i class="fas fa-sign-out-alt logout_icon"></i></button>
      </form>

    </nav>
    `;
  }
}

customElements.define("navigation-component", navigation);
