<template>
  <div>
    <h2>Orders</h2>
    <div class="form-grid">
      <input v-model.number="form.productId" placeholder="Product ID" type="number" />
      <input v-model.number="form.supplierId" placeholder="Supplier ID" type="number" />
      <input v-model.number="form.quantity" placeholder="Quantity" type="number" />
      <input v-model="form.orderDate" placeholder="Order Date" type="date" />
      <select v-model="form.status">
        <option value="PENDING">PENDING</option>
        <option value="SHIPPED">SHIPPED</option>
        <option value="DELIVERED">DELIVERED</option>
      </select>
      <button class="btn-primary" @click="save">{{ editing ? 'Update' : 'Add' }}</button>
    </div>
    <table>
      <thead>
        <tr><th>ID</th><th>Product ID</th><th>Supplier ID</th><th>Qty</th><th>Date</th><th>Status</th><th>Actions</th></tr>
      </thead>
      <tbody>
        <tr v-for="o in orders" :key="o.id">
          <td>{{ o.id }}</td><td>{{ o.productId }}</td><td>{{ o.supplierId }}</td>
          <td>{{ o.quantity }}</td><td>{{ o.orderDate }}</td><td>{{ o.status }}</td>
          <td>
            <button class="btn-edit" @click="edit(o)">Edit</button>
            <button class="btn-danger" @click="remove(o.id)" style="margin-left:6px">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ORDER_BASE_URL } from '@/config/apiConfig'

const API = `${ORDER_BASE_URL}/orders`
const orders = ref([])
const editing = ref(false)
const form = ref({ id: null, productId: null, supplierId: null, quantity: 0, orderDate: '', status: 'PENDING' })

const load = async () => { orders.value = await fetch(API).then(r => r.json()) }

const save = async () => {
  const opts = { method: editing.value ? 'PUT' : 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(form.value) }
  await fetch(editing.value ? `${API}/${form.value.id}` : API, opts)
  reset(); load()
}

const edit = (o) => { form.value = { ...o }; editing.value = true }
const remove = async (id) => { await fetch(`${API}/${id}`, { method: 'DELETE' }); load() }
const reset = () => { form.value = { id: null, productId: null, supplierId: null, quantity: 0, orderDate: '', status: 'PENDING' }; editing.value = false }

onMounted(load)
</script>
