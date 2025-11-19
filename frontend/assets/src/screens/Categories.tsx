import  React, { useState, useEffect } from 'react';
import {View, Text, FlatList, TouchableOpacity, Alert, TextInput, Modal, ActivityIndicator, ScrollView} from 'react-native';
import {categoriesStyles} from '../styles/CategoriesStyles';
import {categoryService, authService} from '../services/api';

export default function CategoriesScreen() {
    const [categories, setCategories] = useState<any[]>([]);
    const [loading, setLoading] = useState(false);
    const [modalVisible, setModalVisible] = useState(false);
    const [editing, setEditing] = useState<any>(null);
    const [formData, setFormData] = useState({ name: '', description: '' });
    const [error, setError] = useState('');
    const [currentUser, setCurrentUser] = useState<any>(null);

    useEffect(() => {
        loadCurrentUser();
        loadCategories();
    }, []);

    const loadCurrentUser = async () => {
        try {
            const user = await authService.getCurrentUser();
            setCurrentUser(user);
        } catch (error) {
            console.error('Error al cargar usuario: ', error);

        }
};
    const loadCategories = async () => {
            setLoading(true);
            setError('');
            try {
                const response = await categoryService.getAll();
                setCategories(response?.data || []);
    }catch (error) {
                setError('Error al cargar categorías.');
                setCategories([]);
            } finally {
                setLoading(false);
            }
    };

    const handlesave = async () => {
        if (!formData.name.trim()) {
            Alert.alert('Validación', 'El nombre es obligatorio.');
            return;
        }

        try {
            if (editing) {
                await categoryService.update(editing.id, formData);
                Alert.alert('Éxito', 'Categoría actualizada exitosamente.');
            } else {
                await categoryService.create(formData);
                Alert.alert('Éxito', 'Categoría creada exitosamente.');
            }
            setModalVisible(false);
            resetForm();
            loadCategories();
        }catch (error) {
            Alert.alert('Error', 'Hubo un problema al guardar la categoría.');
        }

    };


    const handleDelete = (item: any) => {
        if (currentUser?.role !== 'ADMIN') {
            Alert.alert('Permiso denegado', 'No tienes permiso para eliminar categorías, SOLO ADMINS.');
            return;
        }
        Alert.alert('CONFIMAR', `¿Estás seguro de que deseas eliminar la categoría "${item.name}"?`, [
            { text: 'Cancelar', style: 'cancel' }, 
            { 
            text: 'Eliminar', 
            style: 'destructive', 
            onPress: async () => {
                try {
                    
    }
}