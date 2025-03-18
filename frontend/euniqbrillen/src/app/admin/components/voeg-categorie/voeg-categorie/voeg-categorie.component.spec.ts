import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VoegCategorieComponent } from './voeg-categorie.component';

describe('VoegCategorieComponent', () => {
  let component: VoegCategorieComponent;
  let fixture: ComponentFixture<VoegCategorieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VoegCategorieComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VoegCategorieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
