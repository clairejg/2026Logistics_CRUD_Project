import { createRouter, createWebHistory } from 'vue-router'
import Companies from '../views/Companies.vue'
import Suppliers from '../views/Suppliers.vue'
import Orders from '../views/Orders.vue'

const routes = [
  { path: '/', redirect: '/companies' },
  { path: '/companies', component: Companies },
  { path: '/suppliers', component: Suppliers },
  { path: '/orders', component: Orders }
]

export default createRouter({ history: createWebHistory(), routes })
