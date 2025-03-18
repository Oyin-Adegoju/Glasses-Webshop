import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductUpdatenComponent } from './product-updaten.component';

describe('ProductUpdatenComponent', () => {
  let component: ProductUpdatenComponent;
  let fixture: ComponentFixture<ProductUpdatenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductUpdatenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductUpdatenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
