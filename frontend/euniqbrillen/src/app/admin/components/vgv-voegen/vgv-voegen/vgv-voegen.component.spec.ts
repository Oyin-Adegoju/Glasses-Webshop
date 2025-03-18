import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VgvVoegenComponent } from './vgv-voegen.component';

describe('VgvVoegenComponent', () => {
  let component: VgvVoegenComponent;
  let fixture: ComponentFixture<VgvVoegenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VgvVoegenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VgvVoegenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
