Here’s a comprehensive list of **Angular interview questions and answers**, ranging from basic to advanced topics, suitable for freshers as well as experienced professionals:

---

### 🔹 **Basic Level (Beginner)**

#### 1. **What is Angular?**
Angular is a **TypeScript-based open-source front-end web application framework** developed by Google for building SPAs (Single Page Applications).

#### 2. **What are the key features of Angular?**
- Component-based architecture  
- Two-way data binding  
- Dependency Injection  
- Directives  
- Routing  
- RxJS for reactive programming  
- Ahead-of-Time (AOT) Compilation

#### 3. **What is a component in Angular?**
A component is the **basic building block of Angular applications**. It consists of:
- `@Component` decorator
- HTML template
- CSS styles
- TypeScript class

#### 4. **What is a module in Angular?**
An Angular module (`NgModule`) is a **container for components, directives, pipes, and services**. Every Angular app has at least one module - the root module `AppModule`.

#### 5. **What is data binding?**
Data binding in Angular synchronizes **data between the model and view**. Types include:
- **Interpolation**: `{{ value }}`
- **Property binding**: `[property]="value"`
- **Event binding**: `(event)="handler"`
- **Two-way binding**: `[(ngModel)]="value"`

#### 6. **What is Angular CLI?**
Angular CLI is a command-line interface to create and manage Angular projects. Example:  
```bash
ng new app-name
ng generate component my-component
```

---

### 🔹 **Intermediate Level**

#### 7. **What is dependency injection in Angular?**
It’s a design pattern where Angular **automatically provides dependencies** (services, etc.) to a component or directive using constructors.

#### 8. **What is a service in Angular?**
A service is a class that **contains business logic or reusable code** and can be injected into components or other services.

#### 9. **What are directives in Angular?**
Directives are classes that **manipulate the DOM**:
- **Structural Directives**: `*ngIf`, `*ngFor`
- **Attribute Directives**: `ngClass`, `ngStyle`

#### 10. **What is RxJS?**
RxJS (Reactive Extensions for JavaScript) is a library for **reactive programming using Observables**. Angular uses it heavily for asynchronous operations (e.g., HTTP requests).

#### 11. **What is routing in Angular?**
Routing enables **navigation between different views** or components. Defined using `RouterModule` with route paths.

#### 12. **What are lifecycle hooks in Angular?**
Special methods called during the component lifecycle:
- `ngOnInit()`
- `ngOnChanges()`
- `ngDoCheck()`
- `ngAfterViewInit()`  
... and more.

---

### 🔹 **Advanced Level**

#### 13. **What is Change Detection?**
Change detection is the **mechanism that Angular uses to update the DOM** when data changes.

#### 14. **What is the difference between `ngOnInit()` and constructor()?**
- **Constructor**: Used for dependency injection.
- **ngOnInit()**: Called after constructor and component is initialized – ideal for initializing data.

#### 15. **What is lazy loading in Angular?**
Lazy loading loads **modules only when required**, improving performance by splitting the app into smaller bundles.

#### 16. **What is the difference between reactive forms and template-driven forms?**
- **Template-driven**: Simple, declarative, uses `ngModel`
- **Reactive forms**: More complex, programmatic, uses `FormControl`, `FormGroup`

#### 17. **What are guards in Angular routing?**
Guards control access to routes. Types:
- `CanActivate`
- `CanDeactivate`
- `Resolve`
- `CanLoad`

#### 18. **What is Ahead-of-Time (AOT) compilation?**
AOT compiles HTML and TypeScript **into JavaScript during the build phase**, improving performance and early error detection.

#### 19. **What is the difference between `ViewChild` and `ContentChild`?**
- `@ViewChild` accesses template DOM elements.
- `@ContentChild` accesses content projected using `<ng-content>`.

#### 20. **How do you handle HTTP requests in Angular?**
Using `HttpClient` from `@angular/common/http` with Observables:
```ts
this.http.get('api/employees').subscribe(response => {...});
```

---

### 🧠 Bonus Tips:
- Learn common design patterns in Angular (singleton service, smart/dumb components).
- Know about **State Management** (NgRx, BehaviorSubject).
- Familiarize with **Unit Testing** and **e2e testing** using **Jasmine** and **Protractor** or **Cypress**.

---

Would you like a **PDF version**, **coding examples**, or questions grouped by **experience level (0-2, 3-5, 6+ years)**?


---

Here is a curated list of **advanced Angular interview questions** to help you prepare for senior or architect-level roles:

---

## 🔹 **Advanced Angular Interview Questions**

### ✅ **1. What is the difference between `Renderer2` and direct DOM manipulation?**
- `Renderer2` is an Angular abstraction for DOM manipulation that ensures **platform-independent rendering** (e.g., for SSR or web workers).
- Direct DOM (e.g., `document.querySelector`) breaks **Angular's encapsulation** and isn't recommended.

---

### ✅ **2. Explain Angular's Change Detection strategy.**
- Angular uses **zone.js** and a **unidirectional change detection** mechanism.
- Two strategies:
  - `Default`: checks the entire component tree.
  - `OnPush`: checks only if input bindings change, improving performance.

---

### ✅ **3. How does Angular handle memory leaks?**
Memory leaks often happen with:
- Unsubscribed Observables
- EventListeners not removed
To avoid:
- Use `takeUntil()`, `async pipe`, `ngOnDestroy()` for clean-up.

---

### ✅ **4. What is the difference between `ngOnInit()` and `ngAfterViewInit()`?**
- `ngOnInit()` → called once the component is initialized.
- `ngAfterViewInit()` → called once the view (and child components) are fully initialized. Best for DOM access.

---

### ✅ **5. How to optimize performance in large Angular apps?**
- Use `OnPush` change detection
- Lazy loading modules
- Avoid memory leaks
- Use `trackBy` with `*ngFor`
- Preload strategy
- Debounce user inputs

---

### ✅ **6. What is the Angular compiler and how does it work?**
Angular provides two compilers:
- **JIT (Just-in-Time)**: compiles at runtime.
- **AOT (Ahead-of-Time)**: compiles during build, faster loading and better security.

---

### ✅ **7. How is dynamic component loading done in Angular?**
Use `ComponentFactoryResolver` and `ViewContainerRef`:
```ts
const factory = this.resolver.resolveComponentFactory(SomeComponent);
this.container.createComponent(factory);
```

---

### ✅ **8. How does Angular Universal work (SSR)?**
- Angular Universal allows **server-side rendering** for Angular apps.
- It helps with **SEO, faster first load**, and better performance on low-powered devices.

---

### ✅ **9. Explain RxJS operators used in Angular and their use cases.**
- `switchMap`, `mergeMap`, `concatMap`: control inner subscription.
- `debounceTime`, `distinctUntilChanged`: used for search bars.
- `takeUntil`: unsubscribe safely.
- `combineLatest`, `forkJoin`: combine streams.

---

### ✅ **10. What is NgZone and how does it work?**
- `NgZone` helps Angular detect async operations.
- Angular patches all async events (like `setTimeout`, HTTP calls) to trigger change detection automatically.

---

### ✅ **11. What is the purpose of `Injector` and `ReflectiveInjector`?**
- Angular uses `Injector` to resolve dependencies at runtime.
- `ReflectiveInjector` (deprecated) was an older DI API for runtime reflection.

---

### ✅ **12. How does Angular handle form validation under the hood?**
- Template-driven uses `NgModel` and built-in validators.
- Reactive uses `FormGroup`, `FormControl` with validator functions.
- Custom validators return `ValidationErrors | null`.

---

### ✅ **13. What are custom structural directives?**
You can create custom directives using `Directive` and `TemplateRef` to manipulate the DOM, like a custom `*ngIf`.

---

### ✅ **14. What is the difference between `providedIn: 'root'` and in-module providers?**
- `'root'`: service is singleton app-wide.
- Module providers: service is scoped to that module and its children only.

---

### ✅ **15. What is `ngTemplateOutlet` and use cases?**
It allows rendering of templates dynamically.
```html
<ng-container *ngTemplateOutlet="templateRef; context: myContext"></ng-container>
```

---

### ✅ **16. Explain the role of `async` pipe.**
The `async` pipe subscribes to Observables/Promises and handles unsubscribing automatically.

---

### ✅ **17. What is `Defer` block in Angular 17+?**
Introduced to **defer rendering** non-critical content using:
```html
@defer (when condition) {
  <expensive-content />
}
```

---

### ✅ **18. What are standalone components (Angular 14+)?**
- Components that don’t need to be declared in a module.
- Useful for **simplified structure**, especially for libraries and micro frontends.

---

### ✅ **19. Explain `Hydration` in Angular SSR (Angular 16+).**
Hydration reuses server-rendered HTML and **hydrates** it with client-side interactivity, improving performance.

---

### ✅ **20. What is Signal in Angular (Angular 17+)?**
A new **reactivity primitive** (like in React):
```ts
let count = signal(0);
```
Used instead of `BehaviorSubject` or `EventEmitter` for local reactivity.

---

Would you like a **PDF version**, **practice quiz**, or **company-specific question set** for Angular (e.g., TCS, Infosys, etc.)?
