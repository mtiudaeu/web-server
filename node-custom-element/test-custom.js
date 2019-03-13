'use strict'

customElements.define('test-custom',
  class extends HTMLElement {
    constructor() {
      super();
	  
	  const pElem = document.createElement('p');
      pElem.textContent = this.getAttribute('text');

      const shadowRoot = this.attachShadow({mode: 'open'});
      shadowRoot.appendChild(pElem);
	}
  }
 );