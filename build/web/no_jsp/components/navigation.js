class navigation extends HTMLElement {
  constructor() {
    super();
  }
  connectedCallback() {
    this.innerHTML = `
      <style>
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
      
      .fas {
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
      
      /* CONTENT */
      .content {
        width: 100%;
      }      
      </style>
      <nav class="nav_container">

        <h1 class="logo">tourA</h1>

        <section class="section">
        <h2 class="section_title">General</h2>

        <ul class="section_list">
          <li>
            <a href="../../pages/indexPage/index.html" class="section_item">
              <i class="fas fa-home"></i>
              <p class="section_item_text">Inicio</p>
            </a>
          </li>
          <li>
            <a href="" class="section_item">
              <i class="fas fa-clipboard-list"></i>
              <p class="section_item_text">Servicios</p>
            </a>
          </li>
          <li>
            <a href="" class="section_item">
              <i class="fas fa-money-bill-wave"></i>
              <p class="section_item_text">Ventas</p>
            </a>
          </li>
          <li>
            <a href="" class="section_item">
              <i class="fas fa-chart-line"></i>
              <p class="section_item_text">Ganancias</p>
            </a>
          </li>
        </ul>
      </section>

      <section class="section">
        <h2 class="section_title">Usuarios</h2>

        <ul class="section_list">
          <li>
            <a href="../../pages/employeePage/employee.html" class="section_item">
              <i class="fas fa-users-cog"></i>
              <p class="section_item_text">Empleados</p>
            </a>
          </li>
          <li>
            <a href="" class="section_item">
              <i class="fas fa-users"></i>
              <p class="section_item_text">Clientes</p>
            </a>
          </li>
        </ul>
      </section>

    </nav>
    `;
  }
}

customElements.define("navigation-component", navigation);
