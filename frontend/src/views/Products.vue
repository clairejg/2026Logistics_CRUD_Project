<template>
  <div>
    <h2>Inventory — Products</h2>
    <div class="form-grid">
      <input v-model="form.name" placeholder="Name" />
      <input v-model="form.sku" placeholder="SKU" />
      <input v-model="form.category" placeholder="Category" />
      <input v-model.number="form.quantity" placeholder="Quantity" type="number" />
      <input v-model.number="form.unitPrice" placeholder="Unit Price" type="number" />
      <button class="btn-primary" @click="save">{{ editing ? 'Update' : 'Add' }}</button>
    </div>
    <table>
      <thead>
        <tr><th>ID</th><th>Name</th><th>SKU</th><th>Category</th><th>Qty</th><th>Price</th><th>Actions</th></tr>
      </thead>
      <tbody>
        <tr v-for="p in products" :key="p.id">
          <td>{{ p.id }}</td><td>{{ p.name }}</td><td>{{ p.sku }}</td>
          <td>{{ p.category }}</td><td>{{ p.quantity }}</td><td>${{ p.unitPrice }}</td>
          <td>
            <button class="btn-edit" @click="edit(p)">Edit</button>
            <button class="btn-danger" @click="remove(p.id)" style="margin-left:6px">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const API = 'http://localhost:8081/api/products'
const products = ref([])
const editing = ref(false)
const form = ref({ id: null, name: '', sku: '', category: '', quantity: 0, unitPrice: 0 })

const load = async () => { products.value = (await axios.get(API)).data }

const save = async () => {
  if (editing.value) {
    await axios.put(`${API}/${form.value.id}`, form.value)
  } else {
    await axios.post(API, form.value)
  }
  reset(); load()
}

const edit = (p) => { form.value = { ...p }; editing.value = true }
const remove = async (id) => { await axios.delete(`${API}/${id}`); load() }
const reset = () => { form.value = { id: null, name: '', sku: '', category: '', quantity: 0, unitPrice: 0 }; editing.value = false }

onMounted(load)
</script>
