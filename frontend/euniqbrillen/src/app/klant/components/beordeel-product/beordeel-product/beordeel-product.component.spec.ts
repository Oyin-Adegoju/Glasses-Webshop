import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BeordeelProductComponent } from './beordeel-product.component';

describe('BeordeelProductComponent', () => {
  let component: BeordeelProductComponent;
  let fixture: ComponentFixture<BeordeelProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BeordeelProductComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BeordeelProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
