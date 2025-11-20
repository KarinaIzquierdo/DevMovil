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
                    await categoryService.delete(item.id);
                    Alert.alert('Éxito', 'Categoría eliminada exitosamente.');
                    loadCategories();
                } catch (error) {
                    Alert.alert('Error', 'Hubo un problema al eliminar la categoría.');
                }
            }    
    }
]);
};

    const handleToggleActive = (item: any) => {
        const action = item.active ? 'desactivar' : 'activar';
        Alert.alert('confimar', `¿${action.charAt(0).toUpperCase() + action.slice(1)} ${item.name}?`, [
            { text: 'Cancelar', style: 'cancel' }, 
            { 
            text: action.charAt(0).toUpperCase() + action.slice(1),
            onPress: async () => {
                try {
                    await categoryService.update
                    (item.id, {
                    name: item.name, 
                    description: item.description,
                    active: !item.active});

                    Alert.alert('Éxito', `Categoría ${item.active ? 'desactivada' : 'activada'}`);
                    loadCategories();
                } catch (error) {
                    Alert.alert('Error', `No se pudo ${action}`);
                }
            }
        }
        ]);

    };

    const handleEdit = (item: any) => {
        setFormData({ name: item.name, description: item.description || '' } );
        setEditing(item);
        setModalVisible(true);
    };

    const resetForm = () => {
        setFormData({ name: '', description: '' });
        setEditing(null);
    };

    const renderCategory = ({ item } : {item: any}) => (
        <View style={categoriesStyles.categoryCard}>
            <View style = {categoriesStyles.categoryInfo}>
            <Text style={categoriesStyles.categoryName}>
                {item.name} {!item.active && <Text style ={{color: '#999'}}> (Inactiva)}</Text>}
            </Text>
            {item.description && (<Text style={categoriesStyles.categoryDescription}>{item.description}</Text>)}
            </View>
      

            <View style={categoriesStyles.actionContainer}>
                <TouchableOpacity
                    style={[categoriesStyles.actionButton, categoriesStyles.editButtonText]}>Editar   
                    </TouchableOpacity>
                    </View>
                    <view style = {categoriesStyles.}













