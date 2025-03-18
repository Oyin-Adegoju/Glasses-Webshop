import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { KlantService } from 'src/app/klant/service/klant.service';

@Component({
  selector: 'app-bekijk-bestelling',
  templateUrl: './bekijk-bestelling.component.html',
  styleUrls: ['./bekijk-bestelling.component.scss']
})
export class BekijkBestellingComponent {
  orderId: any = this.activatedroute.snapshot.params['orderId'];
  isSpinning = false;
  orderedProductDetailsList: any[] = [];
  totalAmount: any;

  constructor(
    private klantService: KlantService,
    private activatedroute: ActivatedRoute,) { }

  ngOnInit(): void {
    this.getOrderedProductsDetailsByOrderId();
  }

  getOrderedProductsDetailsByOrderId() {
    this.klantService.getOrderedProductsDetailsByOrderId(this.orderId).subscribe((res) => {
      console.log(res);
      res.orderedProductDetailsList.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
        this.orderedProductDetailsList.push(element);
      });
      this.orderedProductDetailsList = res.orderedProductDetailsList;
      this.totalAmount = res.orderAmount;
    })
  }



  
 


}
