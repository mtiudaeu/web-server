'use strict';
import { getUsefulContents } from '/file.js';

const ret =  getUsefulContents('http://www.example.com',
    data => { doSomethingUseful(data); });
console.log(ret);
