import { Component, OnInit } from '@angular/core';
import { GroceryHelperService } from '../shared/grocery-helper/grocery-helper.service';

@Component({
  selector: 'app-grocery-list',
  templateUrl: './grocery-list.component.html',
  styleUrls: ['./grocery-list.component.css']
})
export class GroceryListComponent implements OnInit {

  groceries: Array<any>;

  constructor(private groceryHelperService: GroceryHelperService) { }

  ngOnInit() {
    this.groceryHelperService.getAllGroceries().subscribe(data => {
      this.groceries = data.content;
    });
  }

}
