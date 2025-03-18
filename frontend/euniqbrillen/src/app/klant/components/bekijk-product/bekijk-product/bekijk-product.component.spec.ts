import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BekijkProductComponent } from './bekijk-product.component';

describe('BekijkProductComponent', () => {
  let component: BekijkProductComponent;
  let fixture: ComponentFixture<BekijkProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BekijkProductComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BekijkProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
