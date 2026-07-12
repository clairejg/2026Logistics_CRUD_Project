<template>
  <div>
    <nav class="navbar">
      <span class="brand">📦 Logistics IMS</span>
      <router-link to="/companies">Companies</router-link>
      <router-link to="/suppliers">Suppliers</router-link>
      <router-link to="/orders">Orders</router-link>
    </nav>
    <main class="content">
      <router-view />
    </main>
  </div>
</template>

<style>
* { box-sizing: border-box; margin: 0; padding: 0; font-family: sans-serif; }
.navbar {
  display: flex; align-items: center; gap: 24px;
  background: #1e3a5f; color: white; padding: 14px 24px;
}
.brand { font-size: 1.2rem; font-weight: bold; margin-right: auto; }
.navbar a { color: #cce0ff; text-decoration: none; font-size: 0.95rem; }
.navbar a.router-link-active { color: white; font-weight: bold; border-bottom: 2px solid white; }
.content { padding: 24px; }
table { width: 100%; border-collapse: collapse; margin-top: 16px; }
th, td { padding: 10px 14px; border: 1px solid #ddd; text-align: left; }
th { background: #f0f4f8; }
input, select { padding: 8px; border: 1px solid #ccc; border-radius: 4px; width: 100%; }
button { padding: 8px 16px; border: none; border-radius: 4px; cursor: pointer; }
.btn-primary { background: #1e3a5f; color: white; }
.btn-danger { background: #c0392b; color: white; }
.btn-edit { background: #2980b9; color: white; }
.form-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(180px, 1fr)); gap: 12px; margin-bottom: 16px; }
h2 { margin-bottom: 8px; color: #1e3a5f; }
</style>
