import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ToolbarAppComponent } from './toolbar-app.component';

describe('ToolbarAppComponent', () => {
  let component: ToolbarAppComponent;
  let fixture: ComponentFixture<ToolbarAppComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ToolbarAppComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ToolbarAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
