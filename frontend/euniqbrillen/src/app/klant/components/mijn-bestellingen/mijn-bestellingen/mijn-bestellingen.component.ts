import { Component } from '@angular/core';
import { KlantService } from 'src/app/klant/service/klant.service';

@Component({
  selector: 'app-mijn-bestellingen',
  templateUrl: './mijn-bestellingen.component.html',
  styleUrls: ['./mijn-bestellingen.component.scss']
})
export class MijnBestellingenComponent {
  MyOrders: any;
  isSpinning: boolean;

  constructor(
    private klantService: KlantService,
  ) { }

  ngOnInit(): void {
    this.getMyOrdersByUserId();
  }

  getMyOrdersByUserId() {
    this.isSpinning = true;
    this.klantService.getMyOrdersByUserId().subscribe((res) => {
      this.isSpinning = false;
      this.MyOrders = res;
      console.log(this.MyOrders);
    })
  }

}
