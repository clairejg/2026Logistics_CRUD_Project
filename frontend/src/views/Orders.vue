<template>
  <div>
    <h2>Orders</h2>
    <div class="form-grid">
      <input v-model="form.companyName" placeholder="Company Name" />
      <input v-model="form.productName" placeholder="Product Name" />
      <input v-model="form.country" placeholder="Country" />
      <input v-model="form.state" placeholder="State" />
      <input v-model="form.orderDate" placeholder="Order Date" type="date" />
      <button class="btn-primary" @click="save">{{ editing ? 'Update' : 'Add' }}</button>
      <button v-if="editing" @click="reset" style="background:#888;color:white;padding:8px 16px;border:none;border-radius:4px;cursor:pointer">Cancel</button>
    </div>
    <table>
      <thead>
        <tr>
          <th>Order ID</th>
          <th>Company Name</th>
          <th>Product Name</th>
          <th>Country</th>
          <th>State</th>
          <th>Order Date</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="o in orders" :key="o.orderId">
          <td>{{ o.orderId }}</td>
          <td>{{ o.companyName }}</td>
          <td>{{ o.productName }}</td>
          <td>{{ o.country }}</td>
          <td>{{ o.state }}</td>
          <td>{{ o.orderDate }}</td>
          <td>
            <button class="btn-edit" @click="edit(o)">Edit</button>
            <button class="btn-danger" @click="remove(o.orderId)" style="margin-left:6px">Delete</button>
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
const form = ref({ orderId: null, companyName: '', productName: '', country: '', state: '', orderDate: '' })

const load = async () => { orders.value = await fetch(API).then(r => r.json()) }

const save = async () => {
  const opts = { method: editing.value ? 'PUT' : 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(form.value) }
  await fetch(editing.value ? `${API}/${form.value.orderId}` : API, opts)
  reset(); load()
}

const edit = (o) => { form.value = { ...o }; editing.value = true }
const remove = async (id) => { await fetch(`${API}/${id}`, { method: 'DELETE' }); load() }
const reset = () => { form.value = { orderId: null, companyName: '', productName: '', country: '', state: '', orderDate: '' }; editing.value = false }

onMounted(load)
</script>
