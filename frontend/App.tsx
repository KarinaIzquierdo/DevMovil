import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LoginScreen  from './assets/src/screens/LoginScreen';
import HomeScreen from './assets/src/screens/HomeScreen';
import UsersScreen from './assets/src/screens/UsersScreen';
import CategoriesScreen from './assets/src/screens/CategoriesScreen';
import SubcategoriesScreen from './assets/src/screens/SubcategoriesScreen';
import ProductsScreen from './assets/src/screens/ProductsScreen';

const Stack = createNativeStackNavigator();
export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Login">
        <Stack.Screen name="Login" 
        component={LoginScreen} 
        options={{ headerShown: false }} 
        />
        <Stack.Screen name="Home" 
        component={HomeScreen} 
        options={{ title: 'Menu principal' }} 
        />
        <Stack.Screen 
        name="Users" 
        component={UsersScreen}
        options={{ title: 'Gestion de usuarios' }} 
         />
        <Stack.Screen name="Categories" 
        component={CategoriesScreen}
        options={{ title: 'categorias' }} 
         />
        <Stack.Screen name="Products" 
        component={ProductsScreen} 
        options={{ title: 'Productos' }}
        />
        <Stack.Screen name="SubCategories" 
        component={SubcategoriesScreen} 
        options={{ title: 'Subcategorias' }}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
}