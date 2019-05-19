import {Client} from '../models/Client' ;  
import { OnInit } from '@angular/core';

export class PageClient {
    content : Client[];
    totalPages : number;
    totalElements : number;
    last : boolean;
    size : number ;
    first : boolean ;
    sort : string ;
    numberOfElements : number ;

}