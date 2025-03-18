import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VoegProductComponent } from './voeg-product.component';

describe('VoegProductComponent', () => {
  let component: VoegProductComponent;
  let fixture: ComponentFixture<VoegProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VoegProductComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VoegProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
