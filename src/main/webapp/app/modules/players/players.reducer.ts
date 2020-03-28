import axios from 'axios';

import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

export const ACTION_TYPES = {
  GET_PLAYERS: 'players/GET_PLAYERS',
};

const initialState = {
  loading: false,
  errorMessage: null,
  players: {} as any,
};

export type PlayersState = Readonly<typeof initialState>;

// Reducer

export default (state: PlayersState = initialState, action): PlayersState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.GET_PLAYERS):
      return {
        ...state,
        errorMessage: null,
        loading: true
      };
    case FAILURE(ACTION_TYPES.GET_PLAYERS):
      return {
        ...state,
        loading: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.GET_PLAYERS):
      return {
        ...state,
        loading: false,
        players: action.payload.data
      };
    default:
      return state;
  }
};

// Actions

export const getPlayers = () => ({
  type: ACTION_TYPES.GET_PLAYERS,
  payload: axios.get('api/players')
});
