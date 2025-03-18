import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-bestelling-status',
  templateUrl: './bestelling-status.component.html',
  styleUrls: ['./bestelling-status.component.scss']
})
export class BestellingStatusComponent {
  @Input() data: any;

}
