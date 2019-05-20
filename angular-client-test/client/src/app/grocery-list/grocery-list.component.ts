import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';

import {GroceryHelperService} from '../shared/grocery-helper/grocery-helper.service';
import {Grocery} from '../shared/grocery-helper/grocery';

@Component({
  selector: 'app-grocery-list',
  templateUrl: './grocery-list.component.html',
  styleUrls: ['./grocery-list.component.css']
})
export class GroceryListComponent implements OnInit {

  //Component properties
  allGroceries: Grocery[];
  statusCode: number;
  requestProcessing = false;
  groceryIdToUpdate = null;
  processValidation = false;
  searchValidation = false;
  //Create form
  groceryForm = new FormGroup({
    itemName: new FormControl('', Validators.required),
    category: new FormControl('', Validators.required)
  });

  groceryFormSearch = new FormGroup({
    category: new FormControl('', Validators.required)
  });

  constructor(private groceryHelperService: GroceryHelperService) {
  }

  ngOnInit() {
    this.getAllGroceries();
  }

  //Create ngOnInit() and and load groceries
  //Fetch all groceries
  getAllGroceries() {
    this.groceryHelperService.getAllGroceries()
      .subscribe(
        data => this.allGroceries = data.content,
        errorCode => this.statusCode = errorCode);
  }

  //Handle create and update grocery
  onGroceryFormSubmit() {
    this.processValidation = true;
    if (this.groceryForm.invalid) {
      return; //Validation failed, exit from method.
    }
    //Form is valid, now perform create or update
    this.preProcessConfigurations();
    let itemName = this.groceryForm.get('itemName').value.trim();
    let category = this.groceryForm.get('category').value.trim();
    if (this.groceryIdToUpdate === null) {
      //Handle create grocery
      let grocery = new Grocery(null, itemName, category);
      this.groceryHelperService.createGrocery(grocery)
        .subscribe(successCode => {
            this.statusCode = successCode;
            this.getAllGroceries();
            this.backToCreateGrocery();
          },
          errorCode => this.statusCode = errorCode);
      this.requestProcessing = false;
    } else {
      //Handle update grocery
      let grocery = new Grocery(this.groceryIdToUpdate, itemName, category);
      this.groceryHelperService.updateGrocery(grocery)
        .subscribe(successCode => {
            this.statusCode = successCode;
            this.getAllGroceries();
            this.backToCreateGrocery();
          },
          errorCode => this.statusCode = errorCode);
      this.requestProcessing = false;
    }
  }

  //Handle search by category
  onGroceryFormSearch() {
    this.searchValidation = true;
    if (this.groceryFormSearch.invalid) {
      return; //Validation failed, exit from method.
    }
    //Form is valid, now perform create or update
    this.preProcessConfigurations();
    let category = this.groceryFormSearch.get('category').value.trim();
    //Handle update grocery
    this.groceryHelperService.getGroceryByCategory(category)
      .subscribe(
        data => this.allGroceries = data.content,
        errorCode => this.statusCode = errorCode);
    this.requestProcessing = false;
  }


  //Load grocery by id to edit
  loadGroceryToEdit(id: string) {
    this.preProcessConfigurations();
    this.groceryHelperService.getGroceryById(id)
      .subscribe(grocery => {
          this.groceryIdToUpdate = grocery.id;
          this.groceryForm.setValue({itemName: grocery.itemName, category: grocery.category});
          this.processValidation = true;
          this.requestProcessing = false;
        },
        errorCode => this.statusCode = errorCode);
    this.requestProcessing = false;
  }

  //Delete grocery
  deleteGrocery(id: string) {
    this.preProcessConfigurations();
    this.groceryHelperService.deleteGroceryById(id)
      .subscribe(successCode => {
          this.statusCode = successCode;
          this.getAllGroceries();
          this.backToCreateGrocery();
        },
        errorCode => this.statusCode = errorCode);
    this.requestProcessing = false;
  }

  //Perform preliminary processing configurations
  preProcessConfigurations() {
    this.statusCode = null;
    this.requestProcessing = true;
  }

  //Go back from update to create
  backToCreateGrocery() {
    this.resetGroceryForm();
  }

  //Go back from update to create
  resetGroceryForm() {
    this.groceryIdToUpdate = null;
    this.searchValidation = false;
    this.processValidation = false;
    this.groceryFormSearch.reset();
    this.groceryForm.reset();
  }

}
